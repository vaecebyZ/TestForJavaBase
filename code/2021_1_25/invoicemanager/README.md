# invoicemanager

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).



### Get ElementUI

```bash
npm i element-ui -S
```
### Use ElementUI

in src/main.js

```js
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);
```



#### Get Axios

```bash
npm install axios
```

#### Then

```bash
npm install --save axios vue-axios
```

#### Use Axios

```js
import axios from 'axios'
import VueAxios from 'vue-axios'
 
Vue.use(VueAxios, axios)
```

