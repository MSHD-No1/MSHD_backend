package com.earthquake.managementPlatform.entities;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class GradeEvaluationFactory {
    @Resource
    private GradeEvaluationForDeathStatistics gradeEvaluationForDeathStatistics;
    @Resource
    private GradeEvaluationForInjuredStatistics gradeEvaluationForInjuredStatistics;
    @Resource
    private GradeEvaluationForMissingStatistics gradeEvaluationForMissingStatistics;
    @Resource
    private GradeEvaluationForBrickwoodStructure gradeEvaluationForBrickwoodStructure;
    @Resource
    private GradeEvaluationForCivilStructure gradeEvaluationForCivilStructure;
    @Resource
    private GradeEvaluationForFrameworkStructure gradeEvaluationForFrameworkStructure;
    @Resource
    private GradeEvaluationForMasonryStructure gradeEvaluationForMasonryStructure;
    @Resource
    private GradeEvaluationForOtherStructure gradeEvaluationForOtherStructure;
    private GradeEvaluation gradeEvaluation;

    public GradeEvaluation createGradeEvaluation(String categoryCode) {
        switch (categoryCode) {
            case "111":
                gradeEvaluation = gradeEvaluationForDeathStatistics;
                break;
            case "112":
                gradeEvaluation = gradeEvaluationForInjuredStatistics;
                break;
            case "113":
                gradeEvaluation = gradeEvaluationForMissingStatistics;
                break;
            case "221":
                gradeEvaluation = gradeEvaluationForCivilStructure;
                break;
            case "222":
                gradeEvaluation = gradeEvaluationForBrickwoodStructure;
                break;
            case "223":
                gradeEvaluation = gradeEvaluationForMasonryStructure;
                break;
            case "224":
                gradeEvaluation = gradeEvaluationForFrameworkStructure;
                break;
            case "225":
                gradeEvaluation = gradeEvaluationForOtherStructure;
                break;
        }
        return gradeEvaluation;
    }
}
