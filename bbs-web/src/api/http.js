import axios from 'axios';

const instance = axios.create({
  baseURL: process.env.VUE_APP_API,
  // baseURL: 'http://localhost:3000',
});

export default instance;
