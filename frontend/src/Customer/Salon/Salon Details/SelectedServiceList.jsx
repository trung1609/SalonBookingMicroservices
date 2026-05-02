import React from 'react';
import {IconButton} from "@mui/material";
import {Close} from "@mui/icons-material";

const SelectedServiceList = ({onRemove, selectedServices}) => {
    return (
        <div className={'py-5 space-y-2'}>
            {selectedServices.map((item) =>
                <div key={item.id} className={'p-2 rounded-md bg-slate-100 flex justify-between items-center w-full'}>
                    <div className="flex-1 min-w-0 pr-4">
                        <h1 className="font-thin truncate">
                            {item.name}
                        </h1>
                    </div>
                    <div className="flex items-center gap-3">
                        <p className="font-bold whitespace-nowrap">₹{item.price}</p>
                        <IconButton
                            size="small"
                            onClick={() => onRemove(item.id)}
                            sx={{ p: 0.5 }}
                        >
                            <Close sx={{ fontSize: '18px' }} />
                        </IconButton>
                    </div>
                </div>
            )}
        </div>
    );
};

export default SelectedServiceList;