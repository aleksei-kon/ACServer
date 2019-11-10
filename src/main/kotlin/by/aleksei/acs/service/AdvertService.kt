package by.aleksei.acs.service

import by.aleksei.acs.entities.AdvertInfo
import by.aleksei.acs.entities.OperationStatus
import by.aleksei.acs.entities.db.Advert
import by.aleksei.acs.util.ServiceResponse
import org.springframework.stereotype.Component

@Component
class AdvertService {

    fun add(token: String, advertInfo: AdvertInfo): ServiceResponse<OperationStatus> {
        return ServiceResponse.Success(OperationStatus(false))
    }

    fun update(token: String, advertInfo: AdvertInfo): ServiceResponse<OperationStatus> {
        return ServiceResponse.Success(OperationStatus(false))
    }

    fun delete(token: String, advertId: Int): ServiceResponse<OperationStatus> {
        return ServiceResponse.Success(OperationStatus(false))
    }

    fun get(pageNumber: Int, username: String, sortType: Int): ServiceResponse<List<Advert>> {
        return ServiceResponse.Success(emptyList())
    }
}