package com.fusm.servicebroker.servicebroker.controller.ms_program;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fusm.servicebroker.servicebroker.middleware.IMiddlewareService;
import com.fusm.servicebroker.servicebroker.model.Response;
import com.fusm.servicebroker.servicebroker.model.ms_program.*;
import com.fusm.servicebroker.servicebroker.service.ms_program.IProgramService;
import com.fusm.servicebroker.servicebroker.util.AppRoutes;
import com.fusm.servicebroker.servicebroker.util.Constant;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Clase que expone los servicios necesarios relacionados con los programas
 * ITSense Inc - Andrea Gómez
 */

@RestController
@RequestMapping(value = AppRoutes.PUBLIC_ROUTE + AppRoutes.PROGRAM_ROUTE)
public class ProgramController {

    @Autowired
    private IProgramService programService;

    @Autowired
    private IMiddlewareService middlewareService;


    /**
     * Obtiene los programas según la facultad
     * @return lista de programas
     */
    @GetMapping
    @ApiOperation(
            value = "Get list of programs"
    )
    private ResponseEntity<Response<Object>> getProgram(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, programService.getProgram())
        );
    }

    /**
     * Obtiene los programas según una variedad de facultad
     * @param facultyIds Lista de facultades
     * @return Lista de programas
     */
    @PostMapping("/faculty")
    private ResponseEntity<Response<Object>> getProgramByFaculties(
            @RequestBody List<Integer> facultyIds,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, programService.getProgramsByFaculty(facultyIds))
        );
    }

    /**
     * Obtiene los programas técnicos y tecnológicos según la facultad
     * @param facultyId ID de la facultas
     * @return lista de programas
     */
    @GetMapping("/faculty-id/{id}")
    @ApiOperation(
            value = "Get list of programs by a given facultyId"
    )
    private ResponseEntity<Response<Object>> getProgramByFacultyId(
            @PathVariable("id") Integer facultyId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, programService.getProgramByFaculty(facultyId))
        );
    }

    /**
     * Obtiene los programas técnicos y tecnológicos según la facultad
     * @param facultyId ID de la facultas
     * @return lista de programas
     */
    @GetMapping("/technical/faculty-id/{id}")
    private ResponseEntity<Response<Object>> getTecnicalProgram(
            @PathVariable("id") Integer facultyId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, programService.getTecnicalPrograms(facultyId))
        );
    }

    /**
     * Crea una propuesta de programa académico
     * @param programRequestModel Modelo que contiene la información necesaria para crear una propuesta de programa académico
     * @return OK
     */
    @PostMapping("/proposal")
    @ApiOperation(
            value = "Create proposal"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Header Authorization which value is the user token", required = true, paramType = "header", dataType = "String", example = "Bearer access_token"),
    })
    private ResponseEntity<Response<String>> createProposal(
            @Valid @RequestBody ProgramRequestModel programRequestModel,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(programRequestModel.toString(), Constant.POST_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        programService.createProposal(programRequestModel);
        return ResponseEntity.ok(new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase()));
    }

    /**
     * Actualiza la propuesta académica
     * @param programUpdateModel Modelo que contiene la información necesaria para actualizar una propuesta académica
     * @param proposalId ID de la propuesta
     * @return OK
     */
    @PutMapping("/proposal/{id}")
    @ApiOperation(
            value = "Update proposal"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Header Authorization which value is the user token", required = true, paramType = "header", dataType = "String", example = "Bearer access_token"),
    })
    private ResponseEntity<Response<String>> updateProposal(
            @Valid @RequestBody ProgramUpdateModel programUpdateModel,
            @PathVariable("id") Integer proposalId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(programUpdateModel.toString(), Constant.PUT_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        programService.updateProposal(programUpdateModel, proposalId);
        return ResponseEntity.ok(new Response<>(HttpStatus.OK, HttpStatus.OK.getReasonPhrase()));
    }

    /**
     * Obtiene las propuestas creadas por un usuario
     * @param params parámetros como page y size para paginar la respuesta y facultyId y campusId para filtrar el resultado
     * @param roleRequest contiene la información necesaria para filtrar la consulta
     * @return lista de propuestas académicas
     * @throws JsonProcessingException
     */
    @GetMapping("/proposal")
    @ApiOperation(
            value = "Get proposal"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Header Authorization which value is the user token", required = true, paramType = "header", dataType = "String", example = "Bearer access_token"),
            @ApiImplicitParam(name = "size", value = "Quantity of registers of response", required = false, paramType = "param", dataType = "Integer", example = "10"),
            @ApiImplicitParam(name = "page", value = "Page of the response", required = false, paramType = "param", dataType = "Integer", example = "1"),
            @ApiImplicitParam(name = "campusId", value = "Id of campus to filter the response", required = false, paramType = "param", dataType = "Integer", example = "1"),
    })
    private ResponseEntity<Response<Object>> getProposal(
            @RequestParam Map<String, Object> params,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, programService.getProposal(params))
        );
    }

    /**
     * Lista de programas según su estado (activo, en construcción, desactivados, y declinados)
     * @param params parámetros como page y size para paginar la respuesta y facultyId y campusId para filtrar el resultado
     * @return lista de programas académicos
     * @throws JsonProcessingException
     */
    @GetMapping("/by-status")
    @ApiOperation(
            value = "Get program by a given status (active, declined, review, disabled)"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Header Authorization which value is the user token", required = true, paramType = "header", dataType = "String", example = "Bearer access_token"),
            @ApiImplicitParam(name = "size", value = "Quantity of registers of response", required = false, paramType = "param", dataType = "Integer", example = "10"),
            @ApiImplicitParam(name = "page", value = "Page of the response", required = false, paramType = "param", dataType = "Integer", example = "1"),
            @ApiImplicitParam(name = "facultyId", value = "Id of faculty to filter the response", required = false, paramType = "param", dataType = "Integer", example = "1"),
            @ApiImplicitParam(name = "campusId", value = "Id of campus to filter the response", required = false, paramType = "param", dataType = "Integer", example = "1"),
            @ApiImplicitParam(name = "programStatus", value = "Filter the list on active (get active programs), " +
                    "declined (get declined programs), review (get on construction programs) " +
                    "and disabled (get disabled programs).", required = true, paramType = "param", dataType = "String", example = "active")
    })
    private ResponseEntity<Response<Object>> getProgramByStatus(
            @RequestParam Map<String, Object> params,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, programService.getProgramByStatus(params))
        );
    }

    /**
     * Obtiene el programa por su ID
     * @param programId ID del programa
     * @return programa
     */
    @GetMapping("/{id}")
    @ApiOperation(
            value = "Get details of program by a given id"
    )
    private ResponseEntity<Response<Object>> getProgramById(
            @PathVariable("id") Integer programId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, programService.getProgramById(programId))
        );
    }

    /**
     * Obtiene el histórico de estados de una propuesta
     * @param proposalId ID propuesta
     * @return lista de historial
     */
    @GetMapping("/historic/{id}")
    private ResponseEntity<Response<Object>> getProposalHistoric(
            @PathVariable("id") Integer proposalId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, programService.getProposalHistoric(proposalId))
        );
    }

    /**
     * Obtiene si el programa es una renovación o no
     * @param programId ID del programa
     * @return TRUE o FALSE si el programa es una renovación o no
     */
    @GetMapping("/{id}/is-formal")
    private ResponseEntity<Response<Boolean>> getIsEnlarge(
            @PathVariable("id") Integer programId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, programService.getIsEnlarge(programId))
        );
    }

    /**
     * Actualiza el estado de un programa
     * @param programId ID del programa
     * @return OK
     */
    @GetMapping("/{id}/upgrade-status")
    private ResponseEntity<Response<Integer>> getUpgradeProgramStatus(
            @PathVariable("id") Integer programId,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.PROGRAM_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, programService.getUpdateStatus(programId))
        );
    }

    /**
     * Obtiene la fecha en que se aprovó la propuesta
     * @param programId ID del programa
     * @return DATE
     */
    @GetMapping("/{id}/approved-date")
    private ResponseEntity<Response<Date>> getApprovedProposalDate(
            @PathVariable("id") Integer programId
    ) {
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, programService.getApprovedProposalDate(programId))
        );
    }

    /**
     * Obtiene los programas académicos
     * @param queryFilterProposal Modelo que contiene los parámetros de búsqueda para realizar filtros
     * @return lista de programas académicos
     */
    @PostMapping("/own")
    private ResponseEntity<Response<Object>> getProgramAcademic(
            @RequestBody QueryFilterProposal queryFilterProposal,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        middlewareService.allMiddlewares(null, Constant.GET_METHOD, false, Constant.DASHBOARD_MODULE, authorizationHeader);
        return ResponseEntity.ok(
                new Response<>(HttpStatus.OK, programService.getProgramAcademic(queryFilterProposal))
        );
    }

}