package it.pc.test.WebSpringApp.controller;

import it.pc.test.WebSpringApp.dto.PartnerDTO;
import it.pc.test.WebSpringApp.service.PartnerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/partner")
public class PartnerController extends AbstractBaseController<PartnerDTO, PartnerService, Integer> {

    private PartnerService partnerService;

    @Override
    public PartnerService getService() {
        return partnerService;
    }
}
