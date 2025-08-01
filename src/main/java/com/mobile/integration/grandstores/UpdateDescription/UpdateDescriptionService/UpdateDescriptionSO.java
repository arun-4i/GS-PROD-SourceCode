package com.mobile.integration.grandstores.UpdateDescription.UpdateDescriptionService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mobile.integration.grandstores.PackageCalling.UpdateDescriptionPkg;
import com.mobile.integration.grandstores.UpdateDescription.UpdateDescriptionEntity.ItemConfigEO;
import com.mobile.integration.grandstores.UpdateDescription.UpdateDescriptionRepo.ItemConfigRO;
import com.mobile.integration.grandstores.Utils.ResponseAPI.APIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// -----------
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;


@Service
public class UpdateDescriptionSO  extends NamedParameterJdbcDaoSupport{
    
    @Autowired
    private UpdateDescriptionPkg updateDescriptionpkg;

    @Autowired
    private ItemConfigRO itemConfigRO;
    
    @Autowired
    public void setDataSource(JdbcTemplate jdbcTemplate) {
        super.setDataSource(jdbcTemplate.getDataSource());
    }





    public ResponseEntity<APIResponse> getItemDetail(Map<String, Object> content)  {
        //
        String p_INVENTORY_ITEM_ID=content.get("P_INVENTORY_ITEM_ID")==null ? null : String.valueOf(content.get("P_INVENTORY_ITEM_ID"));
        String p_ITEM_CODE=content.get("P_ITEM_CODE")==null ? null : String.valueOf(content.get("P_ITEM_CODE"));
        String p_ITEM_TYPE=content.get("P_ITEM_TYPE")==null ? null : String.valueOf(content.get("P_ITEM_TYPE"));
        String p_INV_ITEM_STATUS=content.get("P_INV_ITEM_STATUS")==null ? null : String.valueOf(content.get("P_INV_ITEM_STATUS"));
        String p_BUSINESS_UNIT_CODE=content.get("P_BUSINESS_UNIT_CODE")==null ? null : String.valueOf(content.get("P_BUSINESS_UNIT_CODE"));
        String p_PRODUCT_TYPE_CODE=content.get("P_PRODUCT_TYPE_CODE")==null ? null : String.valueOf(content.get("P_PRODUCT_TYPE_CODE"));
        String p_BRAND_CODE=content.get("P_BRAND_CODE")==null ? null : String.valueOf(content.get("P_BRAND_CODE"));
        String p_CATEGORY_CODE=content.get("P_CATEGORY_CODE")==null ? null : String.valueOf(content.get("P_CATEGORY_CODE"));

        Map<String, Object> ls=updateDescriptionpkg.
        getItemDetailPackage(p_INVENTORY_ITEM_ID, p_ITEM_CODE, p_ITEM_TYPE, 
        p_INV_ITEM_STATUS, p_BUSINESS_UNIT_CODE, p_PRODUCT_TYPE_CODE, 
        p_BRAND_CODE, p_CATEGORY_CODE);
 
        APIResponse api=new APIResponse();
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok().body(api); 
    }



    public ResponseEntity<APIResponse> getItemCrossRef(Map<String, Object> content)  {
        //
        String p_INVENTORY_ITEM_ID=content.get("P_INVENTORY_ITEM_ID")==null ? null : String.valueOf(content.get("P_INVENTORY_ITEM_ID"));
        String p_ITEM_CODE=content.get("P_ITEM_CODE")==null ? null : String.valueOf(content.get("P_ITEM_CODE"));
        String p_ITEM_TYPE=content.get("P_ITEM_TYPE")==null ? null : String.valueOf(content.get("P_ITEM_TYPE"));
        String p_INV_ITEM_STATUS=content.get("P_INV_ITEM_STATUS")==null ? null : String.valueOf(content.get("P_INV_ITEM_STATUS"));
        String p_BUSINESS_UNIT_CODE=content.get("P_BUSINESS_UNIT_CODE")==null ? null : String.valueOf(content.get("P_BUSINESS_UNIT_CODE"));
        String p_PRODUCT_TYPE_CODE=content.get("P_PRODUCT_TYPE_CODE")==null ? null : String.valueOf(content.get("P_PRODUCT_TYPE_CODE"));
        String p_BRAND_CODE=content.get("P_BRAND_CODE")==null ? null : String.valueOf(content.get("P_BRAND_CODE"));
        String p_CATEGORY_CODE=content.get("P_CATEGORY_CODE")==null ? null : String.valueOf(content.get("P_CATEGORY_CODE"));

        // 
        // long totProcStartTime = System.currentTimeMillis();
        // System.out.println("Start Time :: " + totProcStartTime + " ms");
        //

        Map<String, Object> ls=updateDescriptionpkg.
        getItemCrossRefPackage(p_INVENTORY_ITEM_ID, p_ITEM_CODE, p_ITEM_TYPE, 
        p_INV_ITEM_STATUS, p_BUSINESS_UNIT_CODE, p_PRODUCT_TYPE_CODE, 
        p_BRAND_CODE, p_CATEGORY_CODE);

        // 
        // long totProcEndTime = System.currentTimeMillis();
        // System.out.println("End Time :: " + totProcEndTime + " ms");
        // System.out.println("total time taken for proc execution for all item :: " + (totProcEndTime - totProcStartTime) + " ms");
        // 
        APIResponse api=new APIResponse();
        // 
        api.setData(ls);
        api.setStatus(HttpStatus.OK.value());
        // System.out.println("USER_ID=>"+UserId);
        // System.out.println("DISP_NAME=>"+UserName);
        return ResponseEntity.ok().body(api); 
    }

// ********************************

public ResponseEntity<APIResponse> insertItemConfig(Iterable<ItemConfigEO> bodydtl) {

    //DUPALL
    List<String> myList = new ArrayList<>();
        String comb = "";
        List<ItemConfigEO> listFromIterator = new ArrayList<>();
        Iterator<ItemConfigEO> iterator1 = bodydtl.iterator();
        while (iterator1.hasNext()) {
            ItemConfigEO current = iterator1.next();
            System.out.println("current.getOrganizationId.toString(): "+current.getOrganizationId().toString());
            
                comb = current.getOrganizationId().toString() + 
                    "-" +current.getItemId().toString() + 
                    "-" +current.getCrossReference().toString();
                System.out.println("inside the iterator: "+comb);
                if(myList.contains(comb)){
                    iterator1.remove();
                    System.out.println("removed");
                }
                else{
                    System.out.println("else part");
                    int countD = recordCrossRef(current.getOrganizationId().toString(), 
                                            current.getItemId().toString(), current.getCrossReference().toString());
                    System.out.println("countD: "+countD);
                    if(countD == 0){
                        System.out.println("inside the countD = 0");
                        listFromIterator.add(current);
                        myList.add(comb);
                    }
                }
                
        }               
        System.out.println("myL size: " +myList.size());
        System.out.println("listFromIterator: " +listFromIterator.size());
        Iterable<ItemConfigEO> ls=itemConfigRO.saveAll(listFromIterator);
        
    //Iterable<ItemConfigEO> ls=itemConfigRO.saveAll(bodydtl);
    APIResponse api=new APIResponse();
    api.setData(ls);
    api.setStatus(HttpStatus.OK.value());
    return ResponseEntity.ok().body(api);
}

private int recordCrossRef(String orgId, String itemId, String crossref) {
    System.out.println("inside recordCrossRef service");
    int c = itemConfigRO.recordCountCrossRef(orgId, itemId, crossref);
    return c;
}





public ResponseEntity<APIResponse> getItemConfig() {
    List<ItemConfigEO> ls=itemConfigRO.findAll();
    APIResponse api=new APIResponse();
    api.setData(ls);
    api.setStatus(HttpStatus.OK.value());    
    return ResponseEntity.ok().body(api);    
}









}