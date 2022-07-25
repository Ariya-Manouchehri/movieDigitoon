package com.example.movie.classes;

import java.util.List;

public class ListMove {
    List<com.example.movie.classes.Search> Search;
    String totalResults;
    String Response;

    public ListMove(List<Search> Search, String totalResults, String response) {
        this.Search = Search;
        this.totalResults = totalResults;
        Response = response;
    }

    public List<com.example.movie.classes.Search> getSearch() {
        return Search;
    }

    public void setSearch(List<com.example.movie.classes.Search> search) {
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
