package com.ip.manager.mapper;

import com.ip.manager.dto.request.AdresseIPRequest;
import com.ip.manager.dto.request.AttackRequest;
import com.ip.manager.entity.AdresseIPEntity;
import com.ip.manager.entity.AttackEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-26T21:47:43+0100",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.33.0.v20230218-1114, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class AttackMapperImpl implements AttackMapper {

    @Override
    public AttackEntity dtoToEntity(AttackRequest attackRequest) {
        if ( attackRequest == null ) {
            return null;
        }

        AttackEntity attackEntity = new AttackEntity();

        attackEntity.setAdresseIP( adresseIPRequestToAdresseIPEntity( attackRequest.getAdresseIP() ) );
        attackEntity.setDate( attackRequest.getDate() );
        attackEntity.setLabel( attackRequest.getLabel() );
        attackEntity.setSeverity( attackRequest.getSeverity() );

        return attackEntity;
    }

    @Override
    public AttackRequest entityToDto(AttackEntity attackEntity) {
        if ( attackEntity == null ) {
            return null;
        }

        AttackRequest attackRequest = new AttackRequest();

        attackRequest.setAdresseIP( adresseIPEntityToAdresseIPRequest( attackEntity.getAdresseIP() ) );
        attackRequest.setDate( attackEntity.getDate() );
        attackRequest.setLabel( attackEntity.getLabel() );
        attackRequest.setSeverity( attackEntity.getSeverity() );

        return attackRequest;
    }

    @Override
    public List<AttackEntity> listDtoToListEntity(List<AttackRequest> listAttacksRequest) {
        if ( listAttacksRequest == null ) {
            return null;
        }

        List<AttackEntity> list = new ArrayList<AttackEntity>( listAttacksRequest.size() );
        for ( AttackRequest attackRequest : listAttacksRequest ) {
            list.add( dtoToEntity( attackRequest ) );
        }

        return list;
    }

    @Override
    public List<AttackRequest> listEtityToListDto(List<AttackEntity> listAttacksEntity) {
        if ( listAttacksEntity == null ) {
            return null;
        }

        List<AttackRequest> list = new ArrayList<AttackRequest>( listAttacksEntity.size() );
        for ( AttackEntity attackEntity : listAttacksEntity ) {
            list.add( entityToDto( attackEntity ) );
        }

        return list;
    }

    protected AdresseIPEntity adresseIPRequestToAdresseIPEntity(AdresseIPRequest adresseIPRequest) {
        if ( adresseIPRequest == null ) {
            return null;
        }

        AdresseIPEntity adresseIPEntity = new AdresseIPEntity();

        adresseIPEntity.setStatus( adresseIPRequest.getStatus() );
        adresseIPEntity.setValueIPV4( adresseIPRequest.getValueIPV4() );

        return adresseIPEntity;
    }

    protected AdresseIPRequest adresseIPEntityToAdresseIPRequest(AdresseIPEntity adresseIPEntity) {
        if ( adresseIPEntity == null ) {
            return null;
        }

        AdresseIPRequest adresseIPRequest = new AdresseIPRequest();

        adresseIPRequest.setStatus( adresseIPEntity.getStatus() );
        adresseIPRequest.setValueIPV4( adresseIPEntity.getValueIPV4() );

        return adresseIPRequest;
    }
}
