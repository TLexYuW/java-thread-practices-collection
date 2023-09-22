```mermaid
flowchart LR
    WW[洗水壺 1 Min]
    Boil[燒開水 15 Min]
    Tea[泡茶]
    WT[洗茶壺 1 Min]
    WC[洗茶杯 2 Min]
    leaf[拿茶葉 1 Min]
    
    WW ==> Boil ==> Tea
    
    WT ==> WC ==> leaf ==> Tea
```