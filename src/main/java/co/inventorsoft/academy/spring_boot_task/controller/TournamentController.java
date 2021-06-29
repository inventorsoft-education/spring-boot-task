package co.inventorsoft.academy.spring_boot_task.controller;

import co.inventorsoft.academy.spring_boot_task.dto.ResultDto;
import co.inventorsoft.academy.spring_boot_task.dto.TeamDto;
import co.inventorsoft.academy.spring_boot_task.mapper.ResultMapper;
import co.inventorsoft.academy.spring_boot_task.mapper.TeamMapper;
import co.inventorsoft.academy.spring_boot_task.service.impl.TournamentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(value = "tournament")
@AllArgsConstructor
public class TournamentController {
    private final TournamentServiceImpl tournamentServiceImpl;
    private final TeamMapper teamMapper;
    private final ResultMapper resultMapper;

    @PostMapping
    public ResponseEntity<HttpStatus> addTeam(@RequestBody TeamDto teamDto ) {
        tournamentServiceImpl.addTeam(teamMapper.toTeam(teamDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "{quantityOfTeam}")
    public ResponseEntity<HttpStatus> createTournament(@PathVariable String quantityOfTeam) {
        tournamentServiceImpl.tossTeams(quantityOfTeam);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "addResult")
    public ResponseEntity<HttpStatus> addResult(@RequestBody ResultDto resultDto) {
        tournamentServiceImpl.writeGameResult(resultMapper.toResult(resultDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "winner")
    public ResponseEntity<HttpStatus> getWinner() {
        tournamentServiceImpl.printTournamentWinner();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "upcomingMatches")
    public ResponseEntity<HttpStatus> lookUpcomingMatches() {
        tournamentServiceImpl.printUpcomingMatches();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
