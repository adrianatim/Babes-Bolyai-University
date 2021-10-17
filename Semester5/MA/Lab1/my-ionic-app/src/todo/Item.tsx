import React from 'react';
import {IonItem, IonLabel} from '@ionic/react';
import {ItemProps} from './ItemProps';

interface ItemPropsExt extends ItemProps {
    onEdit: (id?: string) => void;
}

const Item: React.FC<ItemPropsExt> = ({id, name, age, dob, retired, onEdit}) => {
    return (
        <IonItem onClick={() => onEdit(id)}>
            <IonLabel>{name}</IonLabel>
            <IonLabel>{age}</IonLabel>
            <IonLabel>{dob}</IonLabel>
            <IonLabel>{retired}</IonLabel>
        </IonItem>
    );
};

export default Item;
