package cucerdariancatalin.marvel.mapper

import com.skydoves.sandwich.ApiErrorModelMapper
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message
import cucerdariancatalin.marvel.model.PosterErrorResponse

object ErrorResponseMapper : ApiErrorModelMapper<PosterErrorResponse> {

    /**
     * maps the [ApiResponse.Failure.Error] to the [PosterErrorResponse] using the mapper.
     *
     * @param apiErrorResponse The [ApiResponse.Failure.Error] error response from the network request.
     * @return A customized [PosterErrorResponse] error response.
     */
    override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): PosterErrorResponse {
        return PosterErrorResponse(apiErrorResponse.statusCode.code, apiErrorResponse.message())
    }
}