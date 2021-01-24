package com.hubspot.sendinvitaions.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hubspot.sendinvitaions.api.model.Invitation;
import com.hubspot.sendinvitaions.api.model.Partner;
import com.hubspot.sendinvitaions.api.service.ApiService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/hubspot/send/invitations")
@Slf4j
public class ApiController {

    @Autowired
    private ApiService apiService;

    private List<Partner> partnersList;

    private List<Invitation> invitationsList;


    @GetMapping(value = "/" + "partners")
    @ResponseStatus(HttpStatus.OK)
    public String getPartnersAndSendInvitations() {
        partnersList = apiService.getPartnersAvailability();

        if (CollectionUtils.isEmpty(partnersList)) {
            log.info("Unable to get partners list information");
            return "Unable to get partners list.";
        }

        invitationsList = apiService.getInvitationsList(partnersList);
        return apiService.sendInvitations(invitationsList);
    }
}
