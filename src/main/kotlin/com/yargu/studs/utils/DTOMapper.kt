package com.yargu.studs.utils

import com.yargu.studs.dto.StudentCreateDTO
import com.yargu.studs.entity.Student
import ma.glasnost.orika.MapperFactory
import ma.glasnost.orika.impl.ConfigurableMapper
import org.springframework.stereotype.Component


@Component
class StudentMapper : ConfigurableMapper() {
    public override fun configure(factory: MapperFactory) {
        factory.classMap(Student::class.java, StudentCreateDTO::class.java)
                .byDefault()
                .register()
    }
}
