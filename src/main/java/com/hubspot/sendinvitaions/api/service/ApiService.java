package com.hubspot.sendinvitaions.api.service;

import java.util.List;

import com.hubspot.sendinvitaions.api.model.Invitation;
import com.hubspot.sendinvitaions.api.model.Partner;

public interface ApiService {

    List<Partner> getPartnersAvailability();

    List<Invitation> getInvitationsList(List<Partner> partnersList);

    String sendInvitations(List<Invitation> invitations);

}
