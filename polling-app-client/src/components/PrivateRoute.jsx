import {useMeQuery} from '../features/auth/useMeQuery.js';
import {Navigate, Outlet} from "react-router-dom";

export default function PrivateRoute() {
    const {data: user, isLoading} = useMeQuery();
    if(isLoading) return <div>Loading...</div>;
    return user ? <Outlet /> : <Navigate to="/login" />;
};