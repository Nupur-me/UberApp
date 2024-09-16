package com.project.uber.uberApp.configs;

import com.project.uber.uberApp.dto.PointDTO;
import com.project.uber.uberApp.utils.GeometryUtil;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper =  new ModelMapper();

        //as model mapper have nothing to convert Point so we are doing manually
        //convert PointDTO to point
        mapper.typeMap(PointDTO.class, Point.class).setConverter(converter -> {
            PointDTO pointDTO = converter.getSource();
            return GeometryUtil.createPoint(pointDTO);
        });

        //convert point to PointDTO
        mapper.typeMap(Point.class, PointDTO.class).setConverter(context -> {
            Point point = context.getSource();
            double[] coordinates = {
                    point.getX(),
                    point.getY()
            };
            return new PointDTO(coordinates);
        });


        return mapper;
    }
}
