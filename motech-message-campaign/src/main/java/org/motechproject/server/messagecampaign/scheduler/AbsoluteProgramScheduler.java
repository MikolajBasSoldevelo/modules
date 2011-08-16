package org.motechproject.server.messagecampaign.scheduler;

import org.motechproject.scheduler.MotechSchedulerService;
import org.motechproject.server.messagecampaign.contract.EnrollRequest;
import org.motechproject.server.messagecampaign.domain.campaign.AbsoluteCampaign;
import org.motechproject.server.messagecampaign.domain.message.AbsoluteCampaignMessage;
import org.motechproject.server.messagecampaign.domain.message.CampaignMessage;

import java.util.HashMap;

public class AbsoluteProgramScheduler extends MessageCampaignScheduler {

    public AbsoluteProgramScheduler(MotechSchedulerService schedulerService, EnrollRequest enrollRequest, AbsoluteCampaign campaign) {
        super(schedulerService, enrollRequest, campaign);
    }

    @Override
    protected void scheduleJob(CampaignMessage message) {
        AbsoluteCampaignMessage absoluteCampaignMessage = (AbsoluteCampaignMessage) message;
        HashMap params = jobParams(message);

        scheduleJobOn(enrollRequest.reminderTime(), absoluteCampaignMessage.date(), params);
    }
}
