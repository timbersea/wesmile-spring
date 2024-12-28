package com.qian.wesmile;

import com.qian.wesmile.annotation.Api;
import com.qian.wesmile.annotation.JsonBody;
import com.qian.wesmile.annotation.RelativePath;
import com.qian.wesmile.model.result.Getusersummary;

@Api
public interface Datacube {
    @RelativePath("/datacube/getupstreammsg")
    Getusersummary getusersummary(@JsonBody A a);
}
