package silicar.sample.net.okhttp;

import java.util.List;

/**
 * Created by Tutu on 2016/8/17.
 */
public class UserListVo extends Message {

    /**
     * createdAt : 2016-08-15 18:36:29
     * objectId : jeaw999h
     * updatedAt : 2016-08-15 20:56:33
     * username : tutu.liu
     */

    private List<UserVo> results;

    public List<UserVo> getResults() {
        return results;
    }

    public void setResults(List<UserVo> results) {
        this.results = results;
    }
}
