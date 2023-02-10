package com.itaxi.server.ktxPlace.domain;

import com.itaxi.server.common.BaseEntity;
import com.itaxi.server.ktxPlace.application.dto.UpdateKTXPlaceDto;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Where(clause = "deleted=false")
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class KTXPlace extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private Long cnt;

    @Builder
    public KTXPlace(String name, Long cnt) {
        this.name = name;
        this.cnt = cnt;
    }

    public void updateKTXPlace(UpdateKTXPlaceDto dto) {
        this.name = dto.getName();
    }
}
