package com.pog.generator.controller;


import com.pog.generator.common.CommonResult;
import com.pog.generator.common.QueryParams;
import com.pog.generator.common.ResultPage;
import com.pog.generator.entity.GeneratorEntity;
import com.pog.generator.entity.TableEntity;
import com.pog.generator.service.GeneratorService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/generator")
public class GeneratorController {

    @Autowired
    private GeneratorService generatorService;

    @GetMapping("/table-list")
    @ResponseBody
    public CommonResult<List<TableEntity>> queryTableList(String databaseName){
        List<TableEntity> tableEntities = generatorService.queryTableList(databaseName);
        return CommonResult.success(tableEntities);
    }


    @GetMapping("/database-list")
    @ResponseBody
    public CommonResult<List<String>> queryDatabaseList(){
        List<String> strings = generatorService.fetchDatabaseName();
        return CommonResult.success(strings);
    }

    @PostMapping("/generator")
    public void generatorCode(@RequestBody GeneratorEntity generatorEntity, HttpServletResponse response) throws IOException {

        byte[] data = generatorService.generatorCode(generatorEntity);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"pog-generator.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());

    }
}
