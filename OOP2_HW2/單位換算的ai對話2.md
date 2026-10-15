# you asked

message time: 2026-10-16 00:44:27

你還沒講完

---

# chatgpt response

好，我接著把 **Exercise 2「單位換算器」剩下的程式執行觀念講完**。這次我會特別針對比較難理解的部分，用實際數字一步一步帶你跑程式。

前面已經講到 `JComboBox`、`BorderLayout`、`GridLayout`、事件處理和換算公式，接下來把這些內容串在一起。

## 第十三部分：為什麼 JComboBox 和陣列可以配合？

先看我們的兩個陣列：

```java
String[] lengthUnits = {"公尺", "公分", "英吋", "英尺"};

double[] lengthFactors = {1.0, 0.01, 0.0254, 0.3048};
```

它們的索引是一一對應的。

| 索引 | lengthUnits | lengthFactors |
|--------|--------|--------|
| 0 | 公尺 | 1.0 |
| 1 | 公分 | 0.01 |
| 2 | 英吋 | 0.0254 |
| 3 | 英尺 | 0.3048 |

假設使用者選擇「英吋」。

執行：

```java
int from = fromBox.getSelectedIndex();
```

就會得到：

```java
from = 2;
```

然後：

```java
lengthFactors[from]
```

就等於：

```java
lengthFactors[2]
```

取得 `0.0254`。

也就是說，**我們不需要再寫一大堆 if 判斷目前選的是什麼單位，只要取得索引，就能找到對應倍率。**

---

## 第十四部分：實際模擬一次換算

假設使用者設定：

- 換算類型：長度
- 來源單位：英吋
- 目標單位：公分
- 輸入數值：10

然後按下「換算」。

### 第一步：取得輸入資料

```java
double value = Double.parseDouble(input.getText());

int type = typeBox.getSelectedIndex();
int from = fromBox.getSelectedIndex();
int to = toBox.getSelectedIndex();
```

程式取得：

```java
value = 10.0;
type = 0;
from = 2;
to = 1;
```

### 第二步：判斷換算類型

```java
if (type == 0) {
```

因為 `type = 0`，條件成立，進入長度換算。

### 第三步：轉成公尺

```java
double meters = value * lengthFactors[from];
```

將變數代入：

```java
double meters = 10.0 * lengthFactors[2];
```

因為：

```java
lengthFactors[2] = 0.0254;
```

得到：

```java
meters = 0.254;
```

### 第四步：轉成公分

```java
result = meters / lengthFactors[to];
```

代入：

```java
result = 0.254 / lengthFactors[1];
```

也就是：

```java
result = 0.254 / 0.01;
```

最後得到：

```java
result = 25.4;
```

### 第五步：更新結果框

```java
output.setText(String.format("%.4f", result));
```

`String.format("%.4f", result)` 會產生：

```text
25.4000
```

然後 `setText()` 會把這個字串顯示在 `output` 輸入框。

這次換算就完成了。

---

## 第十五部分：為什麼溫度不能直接乘倍率？

這是這題與一般長度換算最不一樣的地方。

例如長度：

$$
1\text{ 公尺}=100\text{ 公分}
$$

因此只要乘上 100 就可以。

但溫度不同：

$$
0^\circ C=32^\circ F
$$

$$
100^\circ C=212^\circ F
$$

可以發現，即使攝氏是 0，華氏也不是 0。

所以溫度換算需要加減一個偏移值，不能單純乘倍率。

因此我們先把來源溫度轉成攝氏：

```java
double celsius;

if (from == 0) {
    celsius = value;
} else if (from == 1) {
    celsius = (value - 32) * 5 / 9;
} else {
    celsius = value - 273.15;
}
```

再從攝氏轉到目標單位：

```java
if (to == 0) {
    result = celsius;
} else if (to == 1) {
    result = celsius * 9 / 5 + 32;
} else {
    result = celsius + 273.15;
}
```

例如華氏 68 度轉成克氏：

第一步：

$$
(68-32)\times\frac{5}{9}=20
$$

第二步：

$$
20+273.15=293.15
$$

所以結果是 `293.1500`。

這些換算公式屬於完成作業所需的額外補充，不是教授第 57 頁直接提供的公式。

---

## 第十六部分：try、catch 為什麼要使用？

額外補充：Java 例外處理

我們的程式有：

```java
try {
    double value = Double.parseDouble(input.getText());

    // 執行單位換算

} catch (NumberFormatException ex) {
    JOptionPane.showMessageDialog(
        this, "請輸入有效的數字！");
}
```

假設使用者輸入：

```text
abc
```

Java 執行：

```java
Double.parseDouble("abc");
```

但 `abc` 不是有效的數字，因此會產生 `NumberFormatException`。

如果沒有處理這個例外，按鈕事件可能中斷，並在執行環境顯示錯誤資訊。

加入 `try...catch` 後：

1. `try` 嘗試把文字轉成數字。
2. 如果成功，就繼續換算。
3. 如果發生 `NumberFormatException`，就進入 `catch`。
4. 使用 `JOptionPane` 顯示錯誤訊息。

因此使用者輸入錯誤後，仍然可以重新輸入並再次按下換算。

補充一點：`Double.parseDouble()` 也接受 `NaN` 或 `Infinity` 這類特殊字串。如果要做更完整的輸入驗證，可以額外使用 `Double.isFinite()`，但這不是本次作業的必要功能。

---

## 第十七部分：兩個 addActionListener 有什麼不同？

這一點一定要理解，因為 Exercise 2 有兩種事件。

第一種：

```java
typeBox.addActionListener(e -> {

    // 更新單位下拉選單

});
```

第二種：

```java
btn.addActionListener(e -> {

    // 執行單位換算

});
```

雖然兩個都使用 `addActionListener()`，但事件來源不同。

| 事件來源 | 什麼時候執行 | 主要工作 |
|--------|--------|--------|
| `typeBox` | 更換長度、重量、溫度時 | 更新兩個單位選單 |
| `btn` | 按下「換算」時 | 取得數字並計算結果 |

所以當使用者選擇「重量」時，程式不會因此自動執行換算，而是先更新選單。

使用者接著選擇公斤、公克，輸入數值，再按下換算，才會計算。

---

## 第十八部分：完整執行順序

**① main() 開始執行**

**② new UnitConverter()**

**③ 執行建構子，建立視窗與 GUI 元件**

**④ 註冊兩個事件傾聽者**

**⑤ setVisible(true)，顯示視窗**

**⑥ 使用者選擇換算類型與單位**

**⑦ 使用者輸入數字，按下換算**

**⑧ 計算結果，使用 setText 更新 output**

要注意：`main()` 建立完視窗後就會執行完畢，但 Swing 的事件處理機制仍會處理使用者接下來的操作，直到視窗關閉並結束程式。

---

## 第十九部分：有一個可以改進的小地方

我們原本的程式：

```java
output.setText("");
```

只在切換「長度、重量、溫度」時清空結果。

但如果使用者把來源單位從「公尺」改成「英尺」，或修改輸入數字，舊結果仍會顯示，直到再次按下換算。

這不違反教授的題目要求，但可能讓人以為舊結果對應目前的新選擇。

因此比較好的使用習慣是：**每次修改輸入或單位後，再按一次「換算」取得最新結果。**

如果未來你想加上「輸入時自動換算」，那就會用到 `DocumentListener` 等額外的事件處理技術。

---

## 第二十部分：確認你能自己理解程式

**練習題：**

假設使用者輸入：

- 換算類型：重量
- 來源：公斤
- 目標：公克
- 數值：2

程式執行：

```java
double kilograms = value * weightFactors[from];
result = kilograms / weightFactors[to];
```

已知：

```java
weightFactors = {1.0, 0.001, 0.45359237, 0.028349523125};
```

請問 `result` 是多少？

A. 0.002

B. 2

C. 200

D. 2000

這樣就把 Exercise 2 的程式結構、單位陣列、三種換算邏輯、兩個事件、結果更新和錯誤處理全部接起來了。

**目前這題最值得你熟練的三行是：**

```java
typeBox.getSelectedIndex();
fromBox.removeAllItems();
output.setText(String.format("%.4f", result));
```

它們分別對應「取得選擇」、「更新選項」、「更新結果」，也是這題最核心的 GUI 操作。