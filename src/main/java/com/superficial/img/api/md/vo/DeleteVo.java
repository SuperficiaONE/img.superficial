package com.superficial.img.api.md.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class DeleteVo<T> {
    T id;
    List<T> list;
}
