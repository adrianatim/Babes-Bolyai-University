import React, {useContext, useEffect, useState} from 'react';
import {
    IonButton,
    IonButtons,
    IonContent, IonDatetime,
    IonHeader,
    IonInput,
    IonLoading,
    IonPage,
    IonTitle,
    IonToolbar,
    IonCheckbox
} from '@ionic/react';
import {getLogger} from '../core';
import {ItemContext} from './ItemProvider';
import {RouteComponentProps} from 'react-router';
import {ItemProps} from './ItemProps';

const log = getLogger('ItemEdit');

interface ItemEditProps extends RouteComponentProps<{
    id?: string
}> {
}

const ItemEdit: React.FC<ItemEditProps> = ({history, match}) => {
    const {items, saving, savingError, saveItem} = useContext(ItemContext);
    const [name, setName] = useState('');
    const [age, setAge] = useState(0);
    const [dob, setDate] = useState(new Date());
    const [retired, setRetired] = useState(false);
    const [item, setItem] = useState<ItemProps>();
    useEffect(() => {

        log('useEffect');
        const routeId = match.params.id || '';
        const item = items?.find(it => it.id === routeId);
        setItem(item);
        if (item) {
            setName(item.name);
            setAge(item.age)
            setDate(item.dob);
            setRetired(item.retired);
        }
    }, [match.params.id, items]);
    const handleSave = () => {
        const editedItem = item ? {...item, name, age, dob, retired} : {name, age, dob, retired};
        saveItem && saveItem(editedItem).then(() => history.goBack());
    };
    log('render');
    return (
        <IonPage>
            <IonHeader>
                <IonToolbar>
                    <IonTitle>Edit</IonTitle>
                    <IonButtons slot="end">
                        <IonButton onClick={handleSave}>
                            Save
                        </IonButton>
                    </IonButtons>
                </IonToolbar>
            </IonHeader>
            <IonContent>
                <IonInput value={name} onIonChange={e => setName(e.detail.value || '')}/>
                <IonInput value={age} onIonChange={e => setAge(Number(e.detail.value) || 0)}/>
                <IonDatetime displayFormat="MM DD YY" placeholder="Select Date" value={new Date(dob).toDateString()}
                             onIonChange={e => setDate(new Date(e.detail.value || 0))}/>
                <IonCheckbox checked={retired} onIonChange={e => setRetired(e.detail.checked)} />
                <IonLoading isOpen={saving}/>
                {savingError && (
                    <div>{savingError.message || 'Failed to save item'}</div>
                )}
            </IonContent>
        </IonPage>
    );
};

export default ItemEdit;
