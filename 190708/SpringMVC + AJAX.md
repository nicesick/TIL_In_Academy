# SpringMVC + AJAX

## I. JSON DATA

- JSON 을 쓰기위해서는 태그를 하나 추가한다.

```java
@RequestMapping("pchart.mc")
@ResponseBody
// ResponseBody 가 새롭게 추가되었다.

public String pChart() {
    ArrayList<Product> plist = null;
    JSONArray ja = null;

    try {
        plist = pbiz.selectAll();

        ja = new JSONArray();

        for (Product p : plist) {
            JSONObject jo = new JSONObject();

            jo.put("name", p.getName());
            jo.put("y", p.getPrice());

            ja.add(jo);
        }
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

    return ja;
}
```

```javascript
$(document).ready(function(){
    $.ajax({
        // ajax 에서 절대 function 넣지 말 것ㅠㅠ
        url : 'pchart.mc',
        method : 'post',

        success : function(pdata) {
        makeChart(eval(pdata));	
        }
    });
});
```

