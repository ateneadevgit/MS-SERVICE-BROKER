package com.fusm.servicebroker.servicebroker.model.ms_workflow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SyllabusModel {

    private Integer syllabusId;

    @NotNull
    @NotEmpty
    private String code;

    @NotNull
    private Date approvedDate;

    private String subjectCode;

    private String cat;

    private String cine;

    private String nbc;

    @NotNull
    @NotEmpty
    private String attendance;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 300)
    private String modalityObservation;

    @NotNull
    private Integer levelFormationCredits;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 300)
    private String levelFormationPrerequisites;

    @NotNull
    private Integer signatureType;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 300)
    private String signatureTypeObservation;

    private Boolean enabled;
    private String createdBy;
    private Integer workflowBaseId;
    private Integer stepId;
    private Integer curriculumId;
    private String subjectConformation;
    private String subjectContext;
    private String subjectDescription;
    private String learningGeneral;
    private String learningSpecific;
    private String content;
    private String pedagogicalPractices;
    private String bibliographyBasic;
    private String bibliographyLenguaje;
    private String bibliographyWeb;
    private Date createdAt;
    private Date updatedAt;
    private List<Integer> programIds;
    private List<Integer> campusIds;
    private List<Integer> levelFormationIds;
    private List<Integer> facultyIds;
    private List<Integer> modalities;

}
