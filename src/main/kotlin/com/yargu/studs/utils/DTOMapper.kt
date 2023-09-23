package com.yargu.studs.utils

import com.yargu.studs.dto.StudentCreateWithImageDTO
import com.yargu.studs.dto.StudentCreateWithoutImageDTO
import ma.glasnost.orika.MapperFactory
import ma.glasnost.orika.impl.ConfigurableMapper
import org.springframework.stereotype.Component


@Component
class StudentMapper : ConfigurableMapper() {
    public override fun configure(factory: MapperFactory) {
        factory.classMap(StudentCreateWithImageDTO::class.java, StudentCreateWithoutImageDTO::class.java)
                .byDefault()
                .register()
    }
}
