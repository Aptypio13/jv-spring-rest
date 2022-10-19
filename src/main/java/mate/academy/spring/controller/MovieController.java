package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dao.MovieDao;
import mate.academy.spring.dto.request.MovieRequestDto;
import mate.academy.spring.dto.response.MovieResponseDto;
import mate.academy.spring.service.mapper.MovieMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieDao movieDao;
    private final MovieMapper movieMapper;

    public MovieController(MovieDao movieDao, MovieMapper movieMapper) {
        this.movieDao = movieDao;
        this.movieMapper = movieMapper;
    }

    @PostMapping
    public MovieResponseDto addMovie(@RequestBody MovieRequestDto movieRequestDto) {
        return movieMapper.parse(movieDao.add(movieMapper.toModel(movieRequestDto)));
    }

    @GetMapping
    public List<MovieResponseDto> getAllMovies() {
        return movieDao.getAll().stream()
                .map(movieMapper::parse)
                .collect(Collectors.toList());
    }
}