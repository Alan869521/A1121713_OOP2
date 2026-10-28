# you asked

message time: 2026-10-15 21:07:17

![image.png](images/image-001.png)

怎麼會這樣

---

# chatgpt response

你這個不是程式錯誤，而是 **VS Code 的 Java 程式碼提示（Hint）**，所以不用太擔心！

從你的截圖看到：

`New instance ignored (hints(1): 56:9-56:21)`

意思是：

**你建立了一個新的物件，但是沒有把它存進變數，也沒有直接使用它的回傳結果。**

應該是我們之前寫的這段程式：

```java
public static void main(String[] args) {
    new Login();
}
```

### 為什麼會出現提示？

因為：

```java
new Login();
```

會建立一個 `Login` 物件，但沒有使用變數來接收它。

不過我們的 `Login()` 建構子本來就會建立並顯示視窗，所以**這樣寫是可以正常執行的**。

### 怎麼修改？

你可以把原本的：

```java
new Login();
```

改成：

```java
Login frame = new Login();
```

完整寫法：

```java
public static void main(String[] args) {
    Login frame = new Login();
}
```

這樣就有一個變數 `frame` 參考新建立的物件。

但要注意，有些程式碼檢查工具仍可能提示 `frame` 沒有被使用。

### 我的建議

**如果你的登入視窗能正常開啟，就不需要為了這個 Hint 特別修改。**

因為：

- `New instance ignored`：程式碼品質提示，不是編譯錯誤。
- `new Login();`：合法的 Java 寫法。
- 你的建構子有 `setVisible(true)`，因此建立物件時就會顯示視窗。

另外，你的檔名是 `login.java`（小寫 l）。如果程式類別寫的是：

```java
public class Login extends JFrame
```

那麼 Java 檔案名稱應該改成 **`Login.java`**，大小寫要一致。

你可以先按 VS Code 右上角的 **Run（執行）** 看看登入視窗是否正常出現。
