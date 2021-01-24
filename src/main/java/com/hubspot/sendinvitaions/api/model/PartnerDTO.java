package com.hubspot.sendinvitaions.api.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class PartnerDTO {

    private List<Partner> partners;
}
