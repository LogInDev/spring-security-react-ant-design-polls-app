import axios from 'axios';

const api = axios.create({
    baseURL: '/api',
    withCredentials: true, // JWT를 httpOnly 쿠키로 받을 때 필요
});

export default api;