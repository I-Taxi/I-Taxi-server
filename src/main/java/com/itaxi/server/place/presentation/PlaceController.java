package com.itaxi.server.place.presentation;

import com.itaxi.server.docs.ApiDoc;
import com.itaxi.server.exception.bannerPlace.BannerPlaceCntMinusException;
import com.itaxi.server.exception.bannerPlace.BannerPlaceNotNullException;
import com.itaxi.server.place.application.PlaceService;
import com.itaxi.server.place.application.dto.AddPlaceDto;
import com.itaxi.server.place.application.dto.DeletePlaceDto;
import com.itaxi.server.place.application.dto.ResDto;
import com.itaxi.server.place.application.dto.UpdatePlaceDto;
import com.itaxi.server.place.domain.Place;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/place")
public class PlaceController {
    private final PlaceService placeService;

    @ApiOperation(value = ApiDoc.PLACE_READ_FOR_LOOK_UP)
    @RequestMapping(value = "/lookup",method = RequestMethod.GET)
    public ResponseEntity<Iterable<Place>> findPlaceForLookUp() {
        return ResponseEntity.ok(placeService.findPlaceForLookUp());
    }

    @ApiOperation(value = ApiDoc.PLACE_READ_FOR_RECRUIT )
    @RequestMapping(value = "/recruit",method = RequestMethod.GET)
    public ResponseEntity<List> findPlaceForRecruit() {
        return ResponseEntity.ok(placeService.findPlaceForRecruit());
    }

    @ApiOperation(value = ApiDoc.PLACE_CREATE)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResDto create(@RequestBody final AddPlaceDto dto) {return new ResDto(placeService.create(dto));}

    @ApiOperation(value = ApiDoc.PLACE_UPDATE_COUNT)
    @RequestMapping(value = "/count/{id}", method = RequestMethod.PUT)
    public int updateView(@PathVariable final long id, Model model) {
        //Res dto = placeService.findById(id);
        return placeService.updateView(id);
    }
    @ApiOperation(value = ApiDoc.PLACE_UPDATE)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public ResDto update(@PathVariable final long id, @RequestBody final UpdatePlaceDto dto) {
        return new ResDto(placeService.updatePlace(id, dto));
    }

    @ApiOperation(value = ApiDoc.PLACE_DELETE)
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public String delete(@PathVariable final long id) {
        return placeService.deletePlace(id);
    }


}