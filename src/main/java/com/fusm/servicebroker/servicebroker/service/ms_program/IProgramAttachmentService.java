package com.fusm.servicebroker.servicebroker.service.ms_program;

import com.fusm.servicebroker.servicebroker.model.ms_program.GuidelineRequest;
import com.fusm.servicebroker.servicebroker.model.ms_program.SearchModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProgramAttachmentService {

    Object getGuidelineAttachment();
    Object getGuidelineAttachmentByType(Integer type);
    void createGuidelineAttachment(GuidelineRequest guidelineRequest);
    void updateGuidelineAttachment(GuidelineRequest guidelineRequest, Integer guidelineId);
    void disableGuidelineAttachment(Integer guidelineId);
    Object getMinutes(SearchModel searchModel);
    void deleteGuidelineAttachment(Integer guidelineId);
    void enableDisableGuideline(Integer guidelineId, Boolean enabled);
    String getGuidelineAttachmentById(Integer guidelineId);

}
