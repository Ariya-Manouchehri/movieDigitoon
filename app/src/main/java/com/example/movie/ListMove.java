package com.example.movie;

import java.util.ArrayList;
import java.util.List;

public class ListMove {
    List<Search> Search;
    String totalResults;
    String Response;

    public ListMove(List<Search> Search, String totalResults, String response) {
        this.Search = Search;
        this.totalResults = totalResults;
        Response = response;
    }

    public List<com.example.movie.Search> getSearch() {
        return Search;
    }

    public void setSearch(List<com.example.movie.Search> search) {
        Search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }
}
