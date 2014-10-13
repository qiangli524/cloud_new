package com.sitech.shop.webservice.service;


import com.sitech.ssd.billing.vo.user.UserVO;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * 商城用户同步接口
 * Created by Kevin on 14-5-27.
 */
@Path("/userSync")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface UserSync {

    /**
     *
     * @param userVO
     * @return
     */
    @POST
    @Path("/processingUser")
    public UserVO  processing(UserVO userVO)throws Exception;
}
