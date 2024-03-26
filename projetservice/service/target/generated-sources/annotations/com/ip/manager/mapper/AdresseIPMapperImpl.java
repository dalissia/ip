package com.ip.manager.mapper;

import com.ip.manager.dto.request.AdresseIPRequest;
import com.ip.manager.dto.response.AdresseIPResponse;
import com.ip.manager.entity.AdresseIPEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-26T21:47:42+0100",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.33.0.v20230218-1114, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class AdresseIPMapperImpl implements AdresseIPMapper {

    @Override
    public AdresseIPEntity requestToEntity(AdresseIPRequest adresseIPRequest) {
        if ( adresseIPRequest == null ) {
            return null;
        }

        AdresseIPEntity adresseIPEntity = new AdresseIPEntity();

        adresseIPEntity.setStatus( adresseIPRequest.getStatus() );
        adresseIPEntity.setValueIPV4( adresseIPRequest.getValueIPV4() );

        return adresseIPEntity;
    }

    @Override
    public AdresseIPRequest entityToRequest(AdresseIPEntity adresseIPEntity) {
        if ( adresseIPEntity == null ) {
            return null;
        }

        AdresseIPRequest adresseIPRequest = new AdresseIPRequest();

        adresseIPRequest.setStatus( adresseIPEntity.getStatus() );
        adresseIPRequest.setValueIPV4( adresseIPEntity.getValueIPV4() );

        return adresseIPRequest;
    }

    @Override
    public AdresseIPEntity responseToEntity(AdresseIPResponse adresseIPResponse) {
        if ( adresseIPResponse == null ) {
            return null;
        }

        AdresseIPEntity adresseIPEntity = new AdresseIPEntity();

        adresseIPEntity.setNbAttacks( adresseIPResponse.getNbAttacks() );
        adresseIPEntity.setStatus( adresseIPResponse.getStatus() );
        adresseIPEntity.setValueIPV4( adresseIPResponse.getValueIPV4() );

        return adresseIPEntity;
    }

    @Override
    public AdresseIPResponse entityToResponse(AdresseIPEntity adresseIPEntity) {
        if ( adresseIPEntity == null ) {
            return null;
        }

        AdresseIPResponse adresseIPResponse = new AdresseIPResponse();

        adresseIPResponse.setNbAttacks( adresseIPEntity.getNbAttacks() );
        adresseIPResponse.setStatus( adresseIPEntity.getStatus() );
        adresseIPResponse.setValueIPV4( adresseIPEntity.getValueIPV4() );

        return adresseIPResponse;
    }

    @Override
    public List<AdresseIPResponse> listEntityToListResponse(List<AdresseIPEntity> listAdresseIPEntity) {
        if ( listAdresseIPEntity == null ) {
            return null;
        }

        List<AdresseIPResponse> list = new ArrayList<AdresseIPResponse>( listAdresseIPEntity.size() );
        for ( AdresseIPEntity adresseIPEntity : listAdresseIPEntity ) {
            list.add( entityToResponse( adresseIPEntity ) );
        }

        return list;
    }
}
