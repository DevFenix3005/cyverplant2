export * from './plantResource.service';
import { PlantResourceService } from './plantResource.service';
export * from './userResource.service';
import { UserResourceService } from './userResource.service';
export const APIS = [PlantResourceService, UserResourceService];
