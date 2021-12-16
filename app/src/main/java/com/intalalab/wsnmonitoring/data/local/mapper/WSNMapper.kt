package com.intalalab.wsnmonitoring.data.local.mapper

import com.intalalab.wsnmonitoring.core.BaseMapper
import com.intalalab.wsnmonitoring.data.local.model.WSNEntity
import com.intalalab.wsnmonitoring.data.remote.model.wsn.WSNResponseModel
import com.intalalab.wsnmonitoring.util.extension.emptyIfNull
import com.intalalab.wsnmonitoring.util.extension.falseIfNull
import com.intalalab.wsnmonitoring.util.extension.formatJsonDate
import com.intalalab.wsnmonitoring.util.extension.zeroIfNull
import javax.inject.Inject

class WSNMapper @Inject constructor() : BaseMapper<List<WSNResponseModel>, List<WSNEntity>> {

    override fun map(type: List<WSNResponseModel>?): List<WSNEntity> {
        return if(type.isNullOrEmpty()) {
            listOf()
        }
        else {
            type.map {
                WSNEntity(
                    id = it.id.zeroIfNull(),
                    name = it.name.emptyIfNull(),
                    description = it.description.emptyIfNull(),
                    createUserId = it.createUserId.emptyIfNull(),
                    createDate = it.createDate?.formatJsonDate().emptyIfNull(),
                    domain = it.domain.zeroIfNull(),
                    isActive = it.isActive.falseIfNull()
                )
            }
        }
    }

}