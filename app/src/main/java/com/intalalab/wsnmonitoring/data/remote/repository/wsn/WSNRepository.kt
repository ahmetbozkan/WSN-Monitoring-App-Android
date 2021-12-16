package com.intalalab.wsnmonitoring.data.remote.repository.wsn

import com.intalalab.wsnmonitoring.core.Resource
import com.intalalab.wsnmonitoring.data.local.model.WSNEntity
import com.intalalab.wsnmonitoring.data.remote.model.login.LoginResponseModel
import com.intalalab.wsnmonitoring.data.remote.model.wsn.WSNResponseModel

interface WSNRepository {

    /**
     * get list of all WSN (Wireless Sensor Network)
     * @param wsnRequestModel model that contains user info (id, username, password) to get WSN
     * @return WSNParentResponse model that contains list of WSN's
     */
    suspend fun getWSN(wsnRequestModel: LoginResponseModel): Resource<List<WSNEntity>>

}