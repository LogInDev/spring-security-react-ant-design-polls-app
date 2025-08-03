import {useQuery} from "@tanstack/react-query";
import api from '../../api/axios.js';

export function useMeQuery(options) {
    return useQuery({
        queryKey: ['me'],
        queryFn: async () => {
            const {data} = await api.get('/user/me');
            return data;
        },
        ...options,
    });
}