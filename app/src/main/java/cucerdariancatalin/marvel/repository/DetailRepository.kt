package cucerdariancatalin.marvel.repository

import cucerdariancatalin.marvel.persistence.PosterDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DetailRepository constructor(
    private val posterDao: PosterDao
) : Repository {

    fun getPosterById(id: Long) = flow {
        val poster = posterDao.getPoster(id)
        emit(poster)
    }.flowOn(Dispatchers.IO)
}