package edu.pwap.pp.clients;

import java.util.List;

import edu.pwap.pp.models.Note;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi
{
    @GET("posts")
    Call<List<Note>> getNotes();
}
