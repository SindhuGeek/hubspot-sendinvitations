package com.hubspot.sendinvitaions.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hubspot.sendinvitaions.api.dao.ApiDao;
import com.hubspot.sendinvitaions.api.model.Invitation;
import com.hubspot.sendinvitaions.api.model.Partner;
import com.hubspot.sendinvitaions.api.utils.ApiHelper;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private ApiDao apiDao;

    @Override
    public List<Partner> getPartnersAvailability() {
        List<Partner> partnersList = apiDao.getPartnersAvailability();
        return partnersList;
    }

    @Override
    public List<Invitation> getInvitationsList(List<Partner> partnersList) {
        return ApiHelper.checkAvailableDatesAndGetInvitations(partnersList);
    }

    @Override
    public String sendInvitations(List<Invitation> invitations) {
        return apiDao.sendInvitations(invitations);
    }
}
