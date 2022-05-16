package com.example.demo;

import cn.hutool.core.io.file.FileReader;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.example.demo.entity.*;
import com.example.demo.mapper.*;
import com.example.demo.service.*;
import com.example.demo.tablemap.QChinaRegion;
import com.example.demo.tablemap.QMember;
import com.example.demo.tablemap.QProduct;
import com.mbextend.SqlBuilder;
import com.mbextend.SqlQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lvqi
 * @version 1.0.0
 * @since 2022/5/9 17:03
 */
@SpringBootTest
public class DateGenerate {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ChinaRegionMapper chinaRegionMapper;
    @Autowired
    IChinaRegionService regionService;
    @Autowired
    IMemberService memberService;
    @Autowired
    IOrderService orderService;
    @Autowired
    IOrderItemService itemService;
    @Autowired
    IProductService productService;

    /** 中国地区数据生成 */
    @Test
    void generateChinaRegion(){
        File file = new File("src/main/resources/region.txt");
        FileReader fileReader = new FileReader(file);
        List<String> regions = fileReader.readLines();
        List<EbPlace> ebPlaces = regions.stream().map(e -> JSON.parseObject(e, EbPlace.class) ).collect(Collectors.toList());
        List<EbPlace> provinces = new ArrayList<>(33);
        List<EbPlace> citys = new ArrayList<>(340);
        List<EbPlace> districts = new ArrayList<>(2685);
        List<EbPlace> streets = new ArrayList<>(34437);
        ebPlaces.forEach(place -> {
            switch (place.getEbplType()){
                case "PLACE_PROVINCE" :
                    provinces.add(place);
                    break;
                case "PLACE_CITY" :
                    citys.add(place);
                    break;
                case "PLACE_DISTRICT" :
                    districts.add(place);
                    break;
                case "PLACE_STREET" :
                    streets.add(place);
            }
        });
        Map<String, List<EbPlace>> cityGroup = citys.stream().collect(Collectors.groupingBy(EbPlace::getEbplParentPmCode));
        Map<String, List<EbPlace>> districtGroup = districts.stream().collect(Collectors.groupingBy(EbPlace::getEbplParentPmCode));
        Map<String, List<EbPlace>> streetGroup = streets.stream().collect(Collectors.groupingBy(EbPlace::getEbplParentPmCode));
        List<ChinaRegion> provinceList = provinces.stream().map(e -> {
            ChinaRegion region = new ChinaRegion();
            region.setRegionName(e.getEbplNameCn());
            region.setRegionCode(e.getEbplCode().substring(1));
            region.setLatitude(e.getEbplLatitude());
            region.setLongitude(e.getEbplLongitude());
            region.setPostCode(e.getEbplPostCode());
            region.setType(1);
            return region;
        }).collect(Collectors.toList());
        regionService.saveBatch(provinceList);

        provinceList.forEach(p->{
            List<EbPlace> ebPlaces1 = cityGroup.get("1" + p.getRegionCode());
            if(ebPlaces1!=null) {
                List<ChinaRegion> cityList = ebPlaces1.stream().map(e -> {
                    ChinaRegion region = new ChinaRegion();
                    region.setRegionName(e.getEbplNameCn());
                    region.setRegionCode(e.getEbplCode().substring(1));
                    region.setLatitude(e.getEbplLatitude());
                    region.setLongitude(e.getEbplLongitude());
                    region.setPostCode(e.getEbplPostCode());
                    region.setType(2);
                    region.setPid(p.getId());
                    return region;
                }).collect(Collectors.toList());
                regionService.saveBatch(cityList);

                cityList.forEach(c -> {
                    List<EbPlace> ebPlaces2 = districtGroup.get("1" + c.getRegionCode());
                    if (ebPlaces2 != null) {
                        List<ChinaRegion> districtList = ebPlaces2.stream().map(e -> {
                            ChinaRegion region = new ChinaRegion();
                            region.setRegionName(e.getEbplNameCn());
                            region.setRegionCode(e.getEbplCode().substring(1));
                            region.setLatitude(e.getEbplLatitude());
                            region.setLongitude(e.getEbplLongitude());
                            region.setPostCode(e.getEbplPostCode());
                            region.setType(3);
                            region.setPid(c.getId());
                            return region;
                        }).collect(Collectors.toList());
                        regionService.saveBatch(districtList);

                        districtList.forEach(d -> {
                            List<EbPlace> ebPlaces3 = streetGroup.get("1" + d.getRegionCode());
                            if (ebPlaces3 != null) {
                                List<ChinaRegion> streetList = ebPlaces3.stream().map(e -> {
                                    ChinaRegion region = new ChinaRegion();
                                    region.setRegionName(e.getEbplNameCn());
                                    region.setRegionCode(e.getEbplCode().substring(1));
                                    region.setLatitude(e.getEbplLatitude());
                                    region.setLongitude(e.getEbplLongitude());
                                    region.setPostCode(e.getEbplPostCode());
                                    region.setType(4);
                                    region.setPid(d.getId());
                                    return region;
                                }).collect(Collectors.toList());
                                regionService.saveBatch(streetList);
                            }
                        });
                    }
                });
            }
        });
    }

    /** 用户、订单数据生成 */
    @Test
    void generateData(){
        generateMember(1000);
        for (int i = 0; i < 1; i++) {
            generateOrder(10000);
        }
    }

    /** 生成用户数据 */
    private void generateMember(int count){
        Random random = new Random();
        List<String> phoneStart = Arrays.asList("134","135","136","137","138","139","150","151","152","158","159","157","187","188","147","130","131","132","155","156","185","186","133","153","180","189");
        List<String> randAddressList = getRandAddressList(count);
        List<Member> memberList = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            Member member = new Member();
            memberList.add(member);
            member.setUsername("user-"+i);
            member.setBirthday(generateBirthday(10));
            member.setGender(random.nextInt(2)==0?"M":"F");
            member.setPhone(phoneStart.get(random.nextInt(phoneStart.size()))+(random.nextInt(90000000)+10000000));
            member.setMoney(new BigDecimal(100000));
            String[] region = randAddressList.get(i).split(":");
            member.setAddressCode(region[0]);
            member.setAddressName(region[1]);
        }
        memberService.saveBatch(memberList);
    }
    /** 获取随机地址 */
    private List<String> getRandAddressList(int count){
        QChinaRegion qRegion = new QChinaRegion();
        SqlQuery query1 = SqlBuilder.query(qRegion).where(qRegion.type.eq(4)).build();
        List<ChinaRegion> regions4 = chinaRegionMapper.select(query1);
        SqlQuery query2 = SqlBuilder.query(qRegion).where(qRegion.type.eq(3)).build();
        List<ChinaRegion> regions3 = chinaRegionMapper.select(query2);
        SqlQuery query3 = SqlBuilder.query(qRegion).where(qRegion.type.eq(2)).build();
        List<ChinaRegion> regions2 = chinaRegionMapper.select(query3);
        SqlQuery query4 = SqlBuilder.query(qRegion).where(qRegion.type.eq(1)).build();
        List<ChinaRegion> regions1 = chinaRegionMapper.select(query4);

        Map<Integer, List<ChinaRegion>> cityGroup = regions2.stream().collect(Collectors.groupingBy(ChinaRegion::getPid));
        Map<Integer, List<ChinaRegion>> districtGroup = regions3.stream().collect(Collectors.groupingBy(ChinaRegion::getPid));
        Map<Integer, List<ChinaRegion>> streetGroup = regions4.stream().collect(Collectors.groupingBy(ChinaRegion::getPid));

        List<String> data = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < count; i++) {
            ChinaRegion province = regions1.get(random.nextInt(regions1.size()));
            ChinaRegion city = null;
            ChinaRegion district = null;
            ChinaRegion street = null;
            List<ChinaRegion> cities = cityGroup.get(province.getId());
            if(cities!=null){
                city = cities.get(random.nextInt(cities.size()));
                List<ChinaRegion> districts = districtGroup.get(city.getId());
                if(districts!=null) {
                    district = districts.get(random.nextInt(districts.size()));
                    List<ChinaRegion> streets = streetGroup.get(district.getId());
                    if(streets!=null) {
                        street = streets.get(random.nextInt(streets.size()));
                    }
                }
            }
            String addressName = province.getRegionName();
            String addressCode = province.getRegionCode();
            if(city!=null){
                addressName += "-"+ city.getRegionName();
                addressCode = city.getRegionCode();
            }
            if(district!=null){
                addressName += "-"+ district.getRegionName();
                addressCode = district.getRegionCode();
            }
            if(street!=null){
                addressName += "-"+ street.getRegionName();
                addressCode = street.getRegionCode();
            }
            data.add(addressCode+":"+addressName);
        }
        return data;
    }
    /** 获取随机生日 */
    private Date generateBirthday(int maxYear){
        Random random = new Random();
        Calendar calendar = Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        int year = nowYear-maxYear-random.nextInt(90);
        int month = random.nextInt(12);
        int day = random.nextInt(31);
        calendar.set(year,month,day,0,0,0);
        return calendar.getTime();
    }
    /** 生成订单数据 */
    private void generateOrder(int count)  {
        QMember qMember = new QMember();
        List<Member> members = memberMapper.select(SqlBuilder.query(qMember).build());
        QProduct qProduct = new QProduct();
        List<Product> products = productMapper.select(SqlBuilder.query(qProduct).build());

        Random random = new Random();

        List<Order> orderList = new ArrayList<>(count);
        List<OrderItem> orderItemList = new ArrayList<>(count*5);
        for (int j = 0; j < count; j++) {
            Member member = members.get(random.nextInt(members.size()));
            Order order = new Order();
            orderList.add(order);
            order.setId(IdWorker.getIdStr());
            BigDecimal totalPrice = new BigDecimal(0);
            int itemCount = random.nextInt(5) + 1;
            for (int k = 0; k < itemCount; k++) {
                Product product;
                int productCount = 1;
                int percent = random.nextInt(5);
                if(percent==4) {
                    productCount = random.nextInt(5)+1;
                }
                int tryGet = 5;
                while (true){
                    product = products.get(random.nextInt(products.size()));
                    if(product.getStock()>0 && product.getStatus()==1){
                        if(product.getStock()<productCount){
                            productCount = product.getStock();
                        }
                        break;
                    }
                    tryGet--;
                    if(tryGet==0){
                        product = null;
                        break;
                    }
                }
                if(product!=null){
                    OrderItem item = new OrderItem();
                    item.setOrderId(order.getId());
                    BigDecimal price = product.getPrice().multiply(new BigDecimal(productCount));
                    totalPrice = totalPrice.add(price);
                    item.setPrice(price);
                    item.setNumber(productCount);
                    item.setProductId(product.getId());
                    orderItemList.add(item);
                    product.setStock(product.getStock()-productCount);
                }
            }
            if(totalPrice.compareTo(new BigDecimal(0))>0) {
                order.setAddressCode(member.getAddressCode());
                order.setAddressName(member.getAddressName());
                order.setCreateTime(getRandDate(member.getBirthday()));
                order.setMemberId(member.getId());
                order.setTotalPrice(totalPrice);
                if(totalPrice.compareTo(member.getMoney())>0){
                    order.setStatus(1);
                }else{
                    int percent = random.nextInt(7);
                    int status;
                    if(percent==0) {
                        status = random.nextInt(8) + 1;
                    }else{
                        status = 6;
                    }
                    if(status == 6){
                        member.setMoney(member.getMoney().subtract(totalPrice));
                    }
                    order.setStatus(status);
                }
            }
        }
        orderService.saveBatch(orderList);
        itemService.saveBatch(orderItemList);

        members.forEach(m->{
            m.setGender(null);
            m.setBirthday(null);
            m.setPhone(null);
            m.setAddressCode(null);
            m.setAddressName(null);
            m.setUsername(null);
        });
        memberService.updateBatchById(members);

        products.forEach(p->{
            p.setStatus(null);
            p.setPrice(null);
            p.setCategoryId(null);
            p.setName(null);
        });
        productService.updateBatchById(products);
    }
    /** 获取随机日期 */
    private Date getRandDate(Date birthday){
        int days = (int)(System.currentTimeMillis()-birthday.getTime())/1000/3600/24;
        if(days<1){
            return null;
        }
        Random random = new Random();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthday);
        calendar.add(Calendar.DATE,random.nextInt(days)+1);
        return calendar.getTime();
    }
}
