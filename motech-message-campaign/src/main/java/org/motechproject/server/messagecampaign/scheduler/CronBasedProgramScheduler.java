package org.motechproject.server.messagecampaign.scheduler;

import org.joda.time.DateTime;
import org.motechproject.scheduler.MotechSchedulerService;
import org.motechproject.server.messagecampaign.EventKeys;
import org.motechproject.server.messagecampaign.contract.CampaignRequest;
import org.motechproject.server.messagecampaign.domain.campaign.CronBasedCampaign;
import org.motechproject.server.messagecampaign.domain.message.CronBasedCampaignMessage;
import org.motechproject.server.messagecampaign.service.CampaignEnrollmentService;
import org.motechproject.valueobjects.WallTime;

import static org.motechproject.util.DateUtil.newDateTime;

public class CronBasedProgramScheduler extends MessageCampaignScheduler<CronBasedCampaignMessage, CronBasedCampaign> {

    public CronBasedProgramScheduler(MotechSchedulerService schedulerService, CampaignRequest enrollRequest, CronBasedCampaign campaign, CampaignEnrollmentService campaignEnrollmentService) {
        super(schedulerService, enrollRequest, campaign, campaignEnrollmentService);
    }

    @Override
    protected void scheduleJobFor(CronBasedCampaignMessage message) {
        scheduleJobOn(message.cron(), referenceDate(), jobParams(message.messageKey()));
    }

    @Override
    protected DateTime getCampaignEnd() {
        return newDateTime(campaignRequest.referenceDate().plusDays(new WallTime(campaign.maxDuration()).inDays()));
    }

    @Override
    protected String getCampaignMessageSubject(CronBasedCampaignMessage cronBasedCampaignMessage) {
        return EventKeys.MESSAGE_CAMPAIGN_SEND_EVENT_SUBJECT;
    }
}
