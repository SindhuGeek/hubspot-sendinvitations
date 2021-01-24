package com.hubspot.sendinvitaions.api.dao;

import static com.hubspot.sendinvitaions.api.utils.ApiConstants.COUNTRIES;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.hubspot.sendinvitaions.api.model.Invitation;
import com.hubspot.sendinvitaions.api.model.Partner;
import com.hubspot.sendinvitaions.api.model.PartnerDTO;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class ApiDaoImpl implements ApiDao {

    @Value("${hubspot.sendinvitaions.get.partners.url}")
    private String getPartnersUrl;

    @Value("${hubspot.sendinvitations.post.invitation.url}")
    private String postInvitationUrl;

    @Override
    public List<Partner> getPartnersAvailability() {
        RestTemplate restTemplate = new RestTemplate();
        PartnerDTO result = restTemplate.getForObject(getPartnersUrl, PartnerDTO.class);
        return result.getPartners();
    }

    @Override
    public String sendInvitations(List<Invitation> invitationList) {
        String response;
        try {
            Map<String, List<Invitation>> list = new HashMap<>();
            list.put(COUNTRIES, invitationList);

            HttpEntity<Map<String, List<Invitation>>> request = new HttpEntity<>(list);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Invitation> result = restTemplate.postForEntity(postInvitationUrl, request, Invitation.class);
            response = result.getStatusCode().toString();

        } catch (HttpClientErrorException ex) {
            log.error("Exception status code: " + ex.getStatusCode());
            log.error("Exception response body: " + ex.getResponseBodyAsString());
            log.error("Exception during send invitations post request: " + ex.getMessage());
            response = ex.getResponseBodyAsString();
        }
        return response;
    }


}
