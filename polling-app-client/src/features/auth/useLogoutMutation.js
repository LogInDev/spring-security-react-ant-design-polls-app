import {userMutation, useQueryClient, useMutation} from "@tanstack/react-query";
import api from '../../api/axios.js';

export function useLogoutMutation() {
    const queryClient = useQueryClient();
    return useMutation({
        mutationFn: async () => {
            await api.post('/auth/logout');
        },
        onSuccess: () => {
            queryClient.invalidateQueries(['me']);
        },
    });
}