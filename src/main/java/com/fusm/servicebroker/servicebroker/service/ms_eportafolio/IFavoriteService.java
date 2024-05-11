package com.fusm.servicebroker.servicebroker.service.ms_eportafolio;

import com.fusm.servicebroker.servicebroker.model.PageModel;
import com.fusm.servicebroker.servicebroker.model.ms_eportafolio.FavoriteRequest;
import com.fusm.servicebroker.servicebroker.model.ms_eportafolio.FileSearch;
import org.springframework.stereotype.Service;

@Service
public interface IFavoriteService {

    Object getFavoritesFiles(FileSearch fileSearch, PageModel pageModel);
    void addFavorite(FavoriteRequest favoriteRequest, Integer fileId);

}
