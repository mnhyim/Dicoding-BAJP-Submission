package com.mnhyim.moviecatalog.utils

import com.mnhyim.moviecatalog.data.remote.response.MovieResponse
import com.mnhyim.moviecatalog.data.remote.response.ShowResponse

object DummyData {
    fun dummyListMovies(): List<MovieResponse> {
        return listOf(
            MovieResponse(
                567189,
                "Tom Clancy's Without Remorse",
                "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
                "en",
                "2021-04-29",
                7.3,
                "/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
                "/fPGeS6jgdLovQAKunNHX8l0avCy.jpg"
            ),
            MovieResponse(
                460465,
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "en",
                "2021-04-07",
                7.6,
                "/6ELCZlTA5lGUops70hKdB83WJxH.jpg",
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg"
            ),
            MovieResponse(
                460465,
                "Godzilla vs. Kong",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "en",
                "2021-03-24",
                8.1,
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg"
            )
        )
    }

    fun dummyListShows(): List<ShowResponse> {
        return listOf(
            ShowResponse(
                88396,
                "The Falcon and the Winter Soldier",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                listOf("us"),
                "2021-03-19",
                7.9,
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg"
            ),
            ShowResponse(
                60735,
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                listOf("us"),
                "2014-10-07",
                7.7,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg"
            ),
            ShowResponse(
                71712,
                "The Good Doctor",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                listOf("us"),
                "2017-09-25",
                8.6,
                "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                "/mZjZgY6ObiKtVuKVDrnS9VnuNlE.jpg"
            )
        )
    }

    fun dummyMovie(): MovieResponse {
        return MovieResponse(
            460465,
            "Mortal Kombat",
            "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
            "en",
            "2021-04-07",
            7.6,
            "/6ELCZlTA5lGUops70hKdB83WJxH.jpg",
            "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg"
        )
    }

    fun dummyShow(): ShowResponse {
        return ShowResponse(
            60735,
            "The Flash",
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
            listOf("us"),
            "2014-10-07",
            7.7,
            "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
            "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg"
        )
    }
}