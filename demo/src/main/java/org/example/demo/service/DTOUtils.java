package org.example.demo.service;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class DTOUtils {
    private static final ModelMapper modelMapper = new ModelMapper();

    private DTOUtils() {}

    public static <D, T> D map(final T entity, final Class<D> dtoType) {
        if(entity == null) return null;
        return modelMapper.map(entity, dtoType);
    }

    public static <D, T> List<D> mapList(final List<T> entityList, final Class<D> dtoType) {
        return entityList.stream()
                .map(entity -> map(entity, dtoType))
                .collect(Collectors.toList());
    }
}