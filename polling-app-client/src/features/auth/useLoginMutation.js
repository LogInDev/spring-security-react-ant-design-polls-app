import {useMutation, useQueryClient} from "@tanstack/react-query";
import api from '../../api/axios.js';

export function useLoginMutation(){
    const queryClient = useQueryClient();
    return useMutation({
        mutationFn: async (form) =>{
            const {data} = await api.post('/auth/login', form);
            return data;
        },
        onSuccess: () =>{
            queryClient.invalidateQueries(['me']);  // 유저 정보 다시 가져오기
        }
    })
}