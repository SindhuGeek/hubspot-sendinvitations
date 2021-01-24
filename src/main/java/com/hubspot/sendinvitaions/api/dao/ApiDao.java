package com.hubspot.sendinvitaions.api.dao;

import java.util.List;

import com.hubspot.sendinvitaions.api.model.Invitation;
import com.hubspot.sendinvitaions.api.model.Partner;

public interface ApiDao {

    List<Partner> getPartnersAvailability();

    String sendInvitations(List<Invitation> invitationList);
}
