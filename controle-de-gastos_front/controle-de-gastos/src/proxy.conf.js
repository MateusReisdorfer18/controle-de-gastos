const PROXY_CONFIG = [
    {
      context:['/api'],
      target:'http://localhost:8080/controle',
      secure: false,
      changeOrigin: true,
      pathRewrite:{'^/api':''}
    }
  ];
  
module.exports = PROXY_CONFIG;