package com.mnhyim.moviecatalog.utils

import com.mnhyim.moviecatalog.data.local.entity.MovieEntity
import com.mnhyim.moviecatalog.data.local.entity.ShowEntity
import com.mnhyim.moviecatalog.data.remote.response.MovieResponse
import com.mnhyim.moviecatalog.data.remote.response.ShowResponse

object DataMapper {
    fun mapMovieResponseToEntity(input: MovieResponse): MovieEntity =
        MovieEntity(
            id = input.id,
            title = input.title,
            overview = input.overview,
            originalLanguage = input.originalLanguage,
            releaseDate = input.releaseDate,
            voteAverage = input.voteAverage,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath
        )

    fun mapShowResponseToEntity(input: ShowResponse): ShowEntity =
        ShowEntity(
            id = input.id,
            name = input.name,
            overview = input.overview,
            originCountry = input.originCountry,
            firstAirDate = input.firstAirDate,
            voteAverage = input.voteAverage,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath
        )

    fun mapMovieEntitiesToResponse(input: List<MovieEntity>): List<MovieResponse> {
        return input.map {
            MovieResponse(
                id = it.id,
                title = it.title,
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath
            )
        }
    }

    fun mapShowEntitiesToResponse(input: List<ShowEntity>): List<ShowResponse> {
        return input.map {
            ShowResponse(
                id = it.id,
                name = it.name,
                overview = it.overview,
                originCountry = it.originCountry,
                firstAirDate = it.firstAirDate,
                voteAverage = it.voteAverage,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath
            )
        }
    }
}